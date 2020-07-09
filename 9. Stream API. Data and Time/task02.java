// Write a method to get the date n-years m-months and k-days after today using new DateTime API.
// Return the obtained date in the format ISO_LOCAL_DATE.
// * For correct passing of all tests don't use print and println methods in your code.

public static String getDateAfterToday(int years, int months, int days) {
    return LocalDate.now().plus(Period.of(years, months, days)).toString();
}
