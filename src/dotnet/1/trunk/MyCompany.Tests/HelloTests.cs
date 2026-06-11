using System.Net;
using Microsoft.AspNetCore.Mvc.Testing;
using MyCompany.Controllers;
using Xunit;

namespace MyCompany.Tests;

/// <author>dotnet generator</author>
public class HelloTests : IClassFixture<WebApplicationFactory<Program>>
{
    private readonly WebApplicationFactory<Program> _app;

    public HelloTests(WebApplicationFactory<Program> app) => _app = app;

    /// <summary>
    /// A service test that drives the running app over HTTP.
    /// </summary>
    [Fact]
    public async Task IntegrationTest()
    {
        var response = await _app.CreateClient().GetAsync("/");

        Assert.Equal(HttpStatusCode.OK, response.StatusCode);
        Assert.Equal("text/plain; charset=utf-8", response.Content.Headers.ContentType!.ToString());
        Assert.Equal("Hello World!", await response.Content.ReadAsStringAsync());
    }

    /// <summary>
    /// A unit test that calls the controller directly (no HTTP or TCP/IP).
    /// </summary>
    [Fact]
    public void UnitTest()
    {
        var result = new HelloController().Index();

        Assert.Equal("Hello World!", result);
    }
}
