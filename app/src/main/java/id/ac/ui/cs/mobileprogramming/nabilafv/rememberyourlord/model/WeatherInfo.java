package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model;

public class WeatherInfo {
    private String main;
    private String description;
    private String icon;

    public WeatherInfo(String main, String description, String icon) {
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}
