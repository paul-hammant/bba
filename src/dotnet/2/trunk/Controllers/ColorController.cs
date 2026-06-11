using Microsoft.AspNetCore.Mvc;

namespace MyCompany.Controllers;

/// <author>Paul Hammant DevOps, (c) 2018</author>
[ApiController]
public class ColorController : ControllerBase
{
    [HttpGet("/color/hair.json")]
    [Produces("application/json")]
    public ContentResult Hair()
    {
        return new ContentResult
        {
            StatusCode = 200,
            ContentType = "application/json",
            Content = "{\"color\":\"" + ChangingHairColor() + "\"}"
        };
    }

    private static string ChangingHairColor()
    {
        string[] colors = { "Blonde", "Brown", "Black", "Red" };
        return colors[new Random().Next(colors.Length)];
    }
}
