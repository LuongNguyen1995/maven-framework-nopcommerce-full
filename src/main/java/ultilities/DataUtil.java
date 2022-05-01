package ultilities;

import com.github.javafaker.Faker;


public class DataUtil {

    private Faker faker;

    public DataUtil() {
        faker = new Faker();
    }

    public static DataUtil getData(){
        return new DataUtil();
    }

    public String getFirstName(){
        return faker.name().firstName();
    }

    public String getLastName(){

        return faker.name().lastName();
    }

    public String getPassword() {

        return faker.internet().password();
    }

    public String getEmailAddress(){

        return faker.internet().emailAddress();
    }

}
