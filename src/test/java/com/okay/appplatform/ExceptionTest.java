package com.okay.appplatform;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExceptionTest {


    private static final Logger logger = LoggerFactory.getLogger(ExceptionTest.class);

    @Test
    public void testException() {

        int s = 1 / 0;


    }

    @Test
    public void testSperate() {

        System.out.println("file.separator==" + System.getProperty("file.separator"));
        System.out.println("File.separator==" + File.separator);
    }

    @Test
    public void testPrase() {
        String line = "\n" +
                "\n" +
                "\n" +
                "com.okay.studentpad_ui.testcase.personalcenter.PersonalCenterTest:INSTRUMENTATION_STATUS: startTime=1608185376883\n" +
                "\n" +
                "\n" +
                "INSTRUMENTATION_STATUS_CODE: 51\n" +
                "\n" +
                "\n" +
                "INSTRUMENTATION_STATUS: startTime=1608185376886\n" +
                "\n" +
                "\n" +
                "INSTRUMENTATION_STATUS_CODE: 51\n" +
                "\n" +
                "\n" +
                "INSTRUMENTATION_STATUS: model=okay3.1.1\n" +
                "\n" +
                "\n" +
                "INSTRUMENTATION_STATUS_CODE: 53\n" +
                "\n" +
                "\n" +
                "INSTRUMENTATION_STATUS: systemVersion=20201112_T\n" +
                "\n" +
                "\n" +
                "INSTRUMENTATION_STATUS_CODE: 54\n" +
                "\n" +
                "\n" +
                "INSTRUMENTATION_STATUS: apkVersion=2.12.16.8-debug\n" +
                "\n" +
                "\n" +
                "INSTRUMENTATION_STATUS_CODE: 55\n" +
                "\n" +
                "\n" +
                "INSTRUMENTATION_STATUS: wifiName=okay\n" +
                "\n" +
                "\n" +
                "INSTRUMENTATION_STATUS_CODE: 56\n" +
                "\n" +
                "\n" +
                "INSTRUMENTATION_STATUS: module=个人中心\n" +
                "\n" +
                "\n" +
                "INSTRUMENTATION_STATUS_CODE: 200\n" +
                "\n" +
                "\n" +
                "INSTRUMENTATION_STATUS: title=登录dev环境账号\n" +
                "\n" +
                "\n" +
                "INSTRUMENTATION_STATUS_CODE: 200\n" +
                "\n" +
                "\n" +
                "INSTRUMENTATION_STATUS: endTime=1608185401004\n" +
                "\n" +
                "\n" +
                "INSTRUMENTATION_STATUS_CODE: 52\n" +
                "\n" +
                "\n" +
                "INSTRUMENTATION_STATUS: environmentName=NA\n" +
                "\n" +
                "\n" +
                "INSTRUMENTATION_STATUS_CODE: 57\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "Error in testLogin_Dev(com.okay.studentpad_ui.testcase.personalcenter.PersonalCenterTest):\n" +
                "\n" +
                "\n" +
                "junit.framework.AssertionFailedError: 没有找到控件com.okay.s_personalcenter:id/rl_person_account\n" +
                "\n" +
                "\n" +
                "\tat junit.framework.Assert.fail(Assert.java:50)\n" +
                "\n" +
                "\n" +
                "\tat com.okay.studentpad_ui.base.TestBase.getByInfo(TestBase.java:498)\n" +
                "\n" +
                "\n" +
                "\tat com.okay.studentpad_ui.base.TestBase.clickInfo(TestBase.java:449)\n" +
                "\n" +
                "\n" +
                "\tat com.okay.studentpad_ui.base.TestBase.clickById(TestBase.java:618)\n" +
                "\n" +
                "\n" +
                "\tat com.okay.studentpad_ui.function.personalcenter.PersonalCenterFunction.logout(PersonalCenterFunction.java:86)\n" +
                "\n" +
                "\n" +
                "\tat com.okay.studentpad_ui.function.personalcenter.PersonalCenterFunction.studentLogin(PersonalCenterFunction.java:144)\n" +
                "\n" +
                "\n" +
                "\tat com.okay.studentpad_ui.function.personalcenter.PersonalCenterFunction.login(PersonalCenterFunction.java:51)\n" +
                "\n" +
                "\n" +
                "\tat com.okay.studentpad_ui.testcase.personalcenter.PersonalCenterTest.testLogin_Dev(PersonalCenterTest.java:22)\n" +
                "\n" +
                "\n" +
                "\tat java.lang.reflect.Method.invoke(Native Method)\n" +
                "\n" +
                "\n" +
                "\tat java.lang.reflect.Method.invoke(Method.java:372)\n" +
                "\n" +
                "\n" +
                "\tat org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)\n" +
                "\n" +
                "\n" +
                "\tat org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)\n" +
                "\n" +
                "\n" +
                "\tat org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)\n" +
                "\n" +
                "\n" +
                "\tat org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)\n" +
                "\n" +
                "\n" +
                "\tat org.junit.internal.runners.statements.RunBefores.evaluate(RunBefores.java:26)\n" +
                "\n" +
                "\n" +
                "\tat org.junit.internal.runners.statements.RunAfters.evaluate(RunAfters.java:27)\n" +
                "\n" +
                "\n" +
                "\tat org.junit.rules.TestWatcher$1.evaluate(TestWatcher.java:55)\n" +
                "\n" +
                "\n" +
                "\tat org.junit.rules.RunRules.evaluate(RunRules.java:20)\n" +
                "\n" +
                "\n" +
                "\tat org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)\n" +
                "\n" +
                "\n" +
                "\tat org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)\n" +
                "\n" +
                "\n" +
                "\tat org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)\n" +
                "\n" +
                "\n" +
                "\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n" +
                "\n" +
                "\n" +
                "\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n" +
                "\n" +
                "\n" +
                "\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n" +
                "\n" +
                "\n" +
                "\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n" +
                "\n" +
                "\n" +
                "\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n" +
                "\n" +
                "\n" +
                "\tat org.junit.internal.runners.statements.RunBefores.evaluate(RunBefores.java:26)\n" +
                "\n" +
                "\n" +
                "\tat org.junit.internal.runners.statements.RunAfters.evaluate(RunAfters.java:27)\n" +
                "\n" +
                "\n" +
                "\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n" +
                "\n" +
                "\n" +
                "\tat org.junit.runners.Suite.runChild(Suite.java:128)\n" +
                "\n" +
                "\n" +
                "\tat org.junit.runners.Suite.runChild(Suite.java:27)\n" +
                "\n" +
                "\n" +
                "\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n" +
                "\n" +
                "\n" +
                "\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n" +
                "\n" +
                "\n" +
                "\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n" +
                "\n" +
                "\n" +
                "\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n" +
                "\n" +
                "\n" +
                "\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n" +
                "\n" +
                "\n" +
                "\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n" +
                "\n" +
                "\n" +
                "\tat org.junit.runner.JUnitCore.run(JUnitCore.java:137)\n" +
                "\n" +
                "\n" +
                "\tat org.junit.runner.JUnitCore.run(JUnitCore.java:115)\n" +
                "\n" +
                "\n" +
                "\tat android.support.test.internal.runner.TestExecutor.execute(TestExecutor.java:54)\n" +
                "\n" +
                "\n" +
                "\tat android.support.test.runner.AndroidJUnitRunner.onStart(AndroidJUnitRunner.java:240)\n" +
                "\n" +
                "\n" +
                "\tat android.app.Instrumentation$InstrumentationThread.run(Instrumentation.java:1870)\n" +
                "\n" +
                "\n" +
                "java.lang.SecurityException: Permission Denial: getIntentSender() from pid=18262, uid=2000, (need uid=1000) is not allowed to send as package android\n" +
                "\n" +
                "\n" +
                "\tat android.os.Parcel.readException(Parcel.java:1546)\n" +
                "\n" +
                "\n" +
                "\tat android.os.Parcel.readException(Parcel.java:1499)\n" +
                "\n" +
                "\n" +
                "\tat android.view.accessibility.IAccessibilityManager$Stub$Proxy.unregisterUiTestAutomationService(IAccessibilityManager.java:367)\n" +
                "\n" +
                "\n" +
                "\tat android.app.UiAutomationConnection.unregisterUiTestAutomationServiceLocked(UiAutomationConnection.java:313)\n" +
                "\n" +
                "\n" +
                "\tat android.app.UiAutomationConnection.disconnect(UiAutomationConnection.java:106)\n" +
                "\n" +
                "\n" +
                "\tat android.app.IUiAutomationConnection$Stub.onTransact(IUiAutomationConnection.java:66)\n" +
                "\n" +
                "\n" +
                "\tat android.os.Binder.execTransact(Binder.java:451)\n" +
                "\n" +
                "\n" +
                "INSTRUMENTATION_RESULT: shortMsg=java.lang.SecurityException\n" +
                "\n" +
                "\n" +
                "INSTRUMENTATION_RESULT: longMsg=java.lang.SecurityException: Permission Denial: getIntentSender() from pid=18262, uid=2000, (need uid=1000) is not allowed to send as package android\n" +
                "\n" +
                "\n" +
                "INSTRUMENTATION_CODE: 0\n" +
                "\n" +
                "\n";
        String[] content = line.split("\n");
        List<String> lineContents = Arrays.asList(content);
        System.out.println("lineContents==" + lineContents.size());
        long startTime = Long.parseLong(line.split("=")[1].trim());
        System.out.println("startTime==" + startTime);

    }


}
