using System.Text.Json.Serialization;
using Microsoft.AspNetCore.Mvc;

namespace MyCompany.Controllers;

/// <author>Paul Hammant DevOps, (c) 2018</author>
[ApiController]
public class ColorController : ControllerBase
{
    public record HairColor([property: JsonPropertyName("color")] Color Color);

    [HttpGet("/color/hair.json")]
    public HairColor Hair() => new(Colors.RotatingChoice());
}
