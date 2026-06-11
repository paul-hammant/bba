namespace MyCompany;

/// <author>Paul Hammant DevOps, (c) 2018</author>
public class Release3 : IReleaseToggles
{
    public object ChangingHairColor()
    {
        string[] colors = { "Blonde", "Brown", "Black", "Red" };
        return colors[new Random().Next(colors.Length)];
    }
}
