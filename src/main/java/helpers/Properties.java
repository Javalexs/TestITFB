package helpers;

import org.aeonbits.owner.ConfigFactory;

public class Properties {
    /**
     * Поле для хранения значение url адреса считанного из properties файла
     * @author Алексей Фадеев
     */
    public static TestsProperties testsProperties = ConfigFactory.create(TestsProperties.class);
}
