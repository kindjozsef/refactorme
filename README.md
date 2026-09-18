## Purpose of this project

This project is a playground for AI-assisted refactoring. It works correctly,
but the code is intentionally not clean, so an AI agent has something to improve.

The tests are given and describe the expected behavior. They are the safety net
for the refactoring: a refactoring changes the structure of the code, never its
behavior, so the tests must stay green without being modified.

## Checking a refactoring

After the code has been refactored, first format it:

```bash
./gradlew spotlessApply
```

The build checks the formatting, so without this step it fails even when the code
itself is correct. Then run the build, which also runs the tests:

```bash
./gradlew build
```

To see exactly what has changed:

```bash
git diff
```

## Resetting to the original state

To throw away every change and return to the original code:

```bash
git reset --hard && git clean -fd
```

## What it does

`OrderService.calculateTotal(order)` calculates the final price of an order:

1. **Items and tax** – each item's price is multiplied by its quantity, then tax is added
   based on its category: food items are taxed at 9%, everything else at 19%.
2. **Customer discount** – gold customers get 10% off, employees get 20% off.
   Normal customers pay the full price.
3. **Shipping** – orders with a total under 100 pay a flat shipping fee of 15.99.
   Orders of 100 or more ship for free.

The result is rounded to two decimal places. An empty order costs nothing,
   and a missing order is rejected with an `IllegalArgumentException`.

## ReceiptPrinter

`ReceiptPrinter.print(order)` produces a plain text receipt for an order:
the order number, one line per item with its price including tax, the subtotal,
the customer's discount (if any), and the final total.

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
