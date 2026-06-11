using Microsoft.AspNetCore.Mvc;

namespace MyCompany.Controllers;

/// <author>dotnet generator</author>
[ApiController]
public class HelloController : ControllerBase
{
    [HttpGet("/")]
    public string Index() => "Hello World!";
}
