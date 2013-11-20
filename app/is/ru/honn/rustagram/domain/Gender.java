package is.ru.honn.rustagram.domain;

/**
 * Gender enumeration.
 */
public enum Gender {
  MALE, FEMALE, UNSPECIFIED;

    public static Gender fromString(String name){
        Gender gender;
        try{
            gender = Gender.valueOf(name);
        }
        catch(Throwable t){
            gender = UNSPECIFIED;
        }
        return gender;
    }
}
