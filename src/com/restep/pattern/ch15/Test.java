package com.restep.pattern.ch15;

/**
 * Active Objects设计模式(接受异步消息的主动对象)
 *
 * @author restep
 * @date 2019/5/14
 */
public class Test {
    //A a-> B b
    //
    //main
    public static void main(String[] args) {
//        System.gc();
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        new MakerClientThread(activeObject, "Alice").start();
        new MakerClientThread(activeObject, "Bobby").start();

        new DisplayClientThread("Chris", activeObject).start();
    }
}
