package local.kongyu.myFlink.function.generator;

import com.github.javafaker.Faker;
import local.kongyu.myFlink.entity.UserInfoEntity;
import org.apache.flink.api.connector.source.SourceReaderContext;
import org.apache.flink.connector.datagen.source.GeneratorFunction;

import java.util.Date;
import java.util.Locale;

/**
 * 类的模板注释
 *
 * @author 孔余
 * @since 2024-02-29 17:07
 */
public class MyGeneratorFunction implements GeneratorFunction {
    // 创建一个Java Faker实例，指定Locale为中文
    private Faker faker;

    // 初始化随机数数据生成器
    @Override
    public void open(SourceReaderContext readerContext) throws Exception {
        faker = new Faker(new Locale("zh-CN"));
    }

    @Override
    public UserInfoEntity map(Object value) throws Exception {
        // 使用 随机数数据生成器 来创建实例
        UserInfoEntity user = UserInfoEntity.builder()
                .id(faker.number().randomNumber(11, true))
                .name(faker.name().fullName())
                .birthday(faker.date().birthday())
                .age(faker.number().numberBetween(0, 100))
                .province(faker.address().state())
                .city(faker.address().cityName())
                .score(faker.number().randomDouble(3, 1, 100))
                .createTime(new Date())
                .build();
        return user;
    }

}
