package com.example.shapes;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ApplicationTest {
    public static boolean isRunningTest = false;
    public static int fake_select = 0;


    @Before
    public void before() {
        isRunningTest = true;
    }


    @After
    public void after() {
        isRunningTest = false;
    }


    @Test
    public void testMain() {
        ShapeApplication.main(null);
    }

    @Test
    public void testStart() {
        ShapeApplication app = new ShapeApplication();
        try {
            app.start(null);
        } catch (IOException e) {
            Assert.fail();
        }
    }

    @Test
    public void testController() {
        select_zero();

        ShapeController cont = new ShapeController();
        cont.initialize();
        cont.onAddButtonClick();
        cont.onRemoveButtonClick();

        for (int i = 0; i < 3; i++) {
            if (i == 0) cont.onBlueButtonClick();
            else if (i == 1) cont.onGreenButtonClick();
            else cont.onRedButtonClick();
            for (int j = 0; j < 3; j++) {
                if (j == 0) cont.onCircleButtonClick();
                else if (j == 1) cont.onSquareButtonClick();
                else cont.onTriangleButtonClick();

                cont.onAddButtonClick();
                cont.onRemoveButtonClick();
            }
        }

        cont.onSaveButtonClick();

        select_none();

        cont = new ShapeController();
        cont.initialize();
        cont.onAddButtonClick();
        cont.onRemoveButtonClick();
    }

    public void select_zero() {
        fake_select = 0;
    }

    public void select_none() {
        fake_select = -1;
    }
}
