package com.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        JunitTestCreate.class,
        JunitTestInsert.class,
        JunitTestExist.class,
        JunitTestGet.class,
        JunitTestDelete.class
})

public class JunitTest {
}
