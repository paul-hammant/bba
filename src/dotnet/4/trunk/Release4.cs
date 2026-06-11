namespace MyCompany;

/// <author>Paul Hammant DevOps, (c) 2018</author>
public class Release4 : IReleaseToggles
{
    public enum Color
    {
        Blonde,
        Brown,
        Black,
        Red
    }

    private static int _colorCtr = 0;

    public static Color RotatingChoice()
    {
        var values = Enum.GetValues<Color>();
        return values[_colorCtr++ % values.Length];
    }

    public object ChangingHairColor() => RotatingChoice();
}
