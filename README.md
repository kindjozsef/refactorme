# refactorme

# The version catalog
All dependency and plugin versions live in gradle/libs.versions.toml. The build file then refers to them symbolically:

```shell
dependencies {
    implementation(libs.junit.jupiter)
}
```
## Keeping versions up to date
The version-catalog-update plugin resolves the latest available versions and rewrites the TOML file:

```shell
# update every entry, then reformat and sort the catalog
./gradlew versionCatalogUpdate
```
ß
