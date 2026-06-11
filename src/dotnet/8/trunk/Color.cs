using System.Text.Json.Serialization;

namespace MyCompany;

/// <author>Paul Hammant DevOps, (c) 2018</author>
[JsonConverter(typeof(JsonStringEnumConverter<Color>))]
public enum Color
{
    Blonde,
    Brown,
    Black,
    Red
}

public static class Colors
{
    private static int _colorCtr = 0;

    public static Color RotatingChoice()
    {
        var values = Enum.GetValues<Color>();
        return values[_colorCtr++ % values.Length];
    }
}
