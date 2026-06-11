// Paul Hammant DevOps, (c) 2018
namespace MyCompany;

public class Program
{
    public static void Main(string[] args)
    {
        var builder = WebApplication.CreateBuilder(args);
        builder.Services.AddControllers();

        // Boot-time toggle: the implementation is named in configuration
        // (appsettings.json) and instantiated reflectively. Switching old/new
        // is a config change, not a recompile.
        builder.Services.AddSingleton(TogglesFor(builder.Configuration["ReleaseToggles"]!));

        var app = builder.Build();
        app.MapControllers();

        app.Run();
    }

    static IReleaseToggles TogglesFor(string releaseTogglesTypeName)
    {
        var type = Type.GetType(releaseTogglesTypeName)
                   ?? throw new InvalidOperationException($"No such type: {releaseTogglesTypeName}");
        return (IReleaseToggles)Activator.CreateInstance(type)!;
    }
}
