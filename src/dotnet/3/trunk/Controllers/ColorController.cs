using Microsoft.AspNetCore.Mvc;

namespace MyCompany.Controllers;

/// <author>Paul Hammant DevOps, (c) 2018</author>
[ApiController]
public class ColorController : ControllerBase
{
    private readonly IReleaseToggles _releaseToggles;

    public ColorController(IReleaseToggles releaseToggles) => _releaseToggles = releaseToggles;

    [HttpGet("/color/hair.json")]
    [Produces("application/json")]
    public ContentResult Hair()
    {
        return new ContentResult
        {
            StatusCode = 200,
            ContentType = "application/json",
            Content = "{\"color\":\"" + _releaseToggles.ChangingHairColor() + "\"}"
        };
    }
}
