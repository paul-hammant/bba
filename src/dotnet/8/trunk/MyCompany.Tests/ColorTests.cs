using System.Net;
using Microsoft.AspNetCore.Mvc.Testing;
using MyCompany;
using MyCompany.Controllers;
using Xunit;

namespace MyCompany.Tests;

/// <author>Paul Hammant DevOps, (c) 2018</author>
public class ColorTests : IClassFixture<WebApplicationFactory<Program>>
{
    private readonly WebApplicationFactory<Program> _app;

    public ColorTests(WebApplicationFactory<Program> app) => _app = app;

    private static readonly string[] AllowedColors = Enum.GetNames<Color>();

    /// <summary>
    /// A service test that drives the running app over HTTP
    /// to check hair color functionality.
    /// </summary>
    [Fact]
    public async Task ServiceTest()
    {
        var response = await _app.CreateClient().GetAsync("/color/hair.json");
        var body = await response.Content.ReadAsStringAsync();

        Assert.Equal(HttpStatusCode.OK, response.StatusCode);
        Assert.Equal("application/json", response.Content.Headers.ContentType!.MediaType);
        Assert.StartsWith("{\"color\":\"", body);
        Assert.EndsWith("\"}", body);
        Assert.Contains(AllowedColors, c => body.Contains(c));
    }

    /// <summary>
    /// A unit test that checks the 'new' enum-based implementation directly
    /// (without HTTP or TCP/IP).
    /// </summary>
    [Fact]
    public void NewHairColorTest()
    {
        var hair = new ColorController().Hair();

        // A typed object here, not a JSON string -
        // an instance of that enum to be specific.
        Assert.Contains(hair.Color, Enum.GetValues<Color>());
    }
}
