package io.arrogantprogrammer.mutiny.superherotacos.api.domain.tacos;

import java.net.URL;

public class Condiment {

    private String slug;

    private String recipe;

    private String name;

    private URL url;

    public Condiment(String slug, String recipe, String name, URL url) {
        this.slug = slug;
        this.recipe = recipe;
        this.name = name;
        this.url = url;
    }

    public Condiment() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Condiment{");
        sb.append("slug='").append(slug).append('\'');
        sb.append(", recipe='").append(recipe).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", url=").append(url);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Condiment condiment = (Condiment) o;

        if (slug != null ? !slug.equals(condiment.slug) : condiment.slug != null) return false;
        if (recipe != null ? !recipe.equals(condiment.recipe) : condiment.recipe != null) return false;
        if (name != null ? !name.equals(condiment.name) : condiment.name != null) return false;
        return url != null ? url.equals(condiment.url) : condiment.url == null;
    }

    @Override
    public int hashCode() {
        int result = slug != null ? slug.hashCode() : 0;
        result = 31 * result + (recipe != null ? recipe.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

}
