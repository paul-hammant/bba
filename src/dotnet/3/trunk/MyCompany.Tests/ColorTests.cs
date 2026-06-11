using System.Net;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Testing;
using MyCompany.Controllers;
using Xunit;

namespace MyCompany.Tests;

/// <author>Paul Hammant DevOps, (c) 2018</author>
public class ColorTests : IClassFixture<WebApplicationFactory<Program>>
{
    private readonly WebApplicationFactory<Program> _app;

    public ColorTests(WebApplicationFactory<Program> app) => _app = app;

    private static readonly string[] AllowedColors = { "Blonde", "Brown", "Black", "Red" };

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
    /// A unit test that checks hair color functionality
    /// without HTTP or TCP/IP.
    /// </summary>
    [Fact]
    public void UnitTest()
    {
        var result = (ContentResult)new ColorController(new Release3()).Hair();

        Assert.Equal(200, result.StatusCode);
        Assert.Equal("application/json", result.ContentType);
        Assert.StartsWith("{\"color\":\"", result.Content);
        Assert.EndsWith("\"}", result.Content);
        Assert.Contains(AllowedColors, c => result.Content!.Contains(c));
    }
}
