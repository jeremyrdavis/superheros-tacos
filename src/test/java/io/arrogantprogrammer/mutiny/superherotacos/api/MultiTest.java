package io.arrogantprogrammer.mutiny.superherotacos.api;

import io.smallrye.mutiny.Multi;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class MultiTest {

    @Test
    public void test() {
        Multi<Integer> multi = Multi.createFrom()
                .iterable(Arrays.asList(1,3,5,7,9));
        List<Integer> list = multi
                .select().where(i -> i > 6)
                .collect().asList()
                .await().indefinitely();
        List<Integer> list1 = Multi.createFrom()
                .iterable(Arrays.asList(1,3,5,7,9))
                .select().where(i -> i > 6)
                .collect().asList().await().indefinitely();
    }
}
