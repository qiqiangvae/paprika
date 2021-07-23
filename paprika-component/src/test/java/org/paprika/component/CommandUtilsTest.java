package org.paprika.component;

/**
 * @author qiqiang
 */
public class CommandUtilsTest {
    public static void main(String[] args) {
        CommandUtils.exec("cd .. & pwd");
        CommandUtils.exec("git --help");
    }
}