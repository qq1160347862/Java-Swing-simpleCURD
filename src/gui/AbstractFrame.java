package gui;
import gui.service.AbstractService;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
/**
 * AbstractFrame 是项目的顶层抽象类，继承JFrame
 * - 每个窗口对应一个Service类，实现前端数据的接收、处理和渲染
 * - 每个窗口的逻辑结构为：
 * - AbstractFrame <-> AbstractService <-> controllers
 * - 即AbstractFrame只需要负责窗口样式搭建即可，实际业务交给AbstractService处理
 */
public abstract class AbstractFrame <R extends AbstractService> extends JFrame {
    //当前窗口业务类
    protected R service;
    //当前窗口的组件列表
    private final Map<String, JComponent> componentMap = new HashMap<>();
    /**
     * 抽象类AbstractFrame的构造方法,具体子类调用super()初始化
     * @param title
     * @param Size
     * @param resizeable
     * @param closeOperation
     */
    protected AbstractFrame(String title, Dimension Size, boolean resizeable, int closeOperation){
        this.setSize(Size);         //设置窗口大小
        this.setTitle(title);       //设置窗口标题
        this.setDefaultCloseOperation(closeOperation);          //设置窗口关闭方式
        this.setResizable(resizeable);          //设置窗口尺寸再调整
        this.setLocationRelativeTo(null);       //设置窗口位置居中
    }
    /**
     * 窗口的初始化方法，由具体子类实现,即样式布局的代码均在这里实现
     */
    protected abstract void init();
    /**
     * 为顶层容器JFrame添加组件
     * @param name  //组件名称
     * @param jComponent    //添加的组件
     * @param consumer      //组件配置
     * ps: Consumer<T>是一个功能接口，使用lambda表达式来定义形参并通过调用.accept(T)来指定传入的实际参数，并且执行代码块{...}
     * @param <T>   //泛型为JComponent
     */
    protected final <T extends JComponent> void addComponent(String name, T jComponent, Consumer<T> consumer){
        if(consumer != null){
            consumer.accept(jComponent);
        }
        this.componentMap.put(name, jComponent);
        this.add(jComponent);
    }
    /**
     * 为顶层容器JFrame添加组件(带组件约束)
     * this.addComponent("addBtn",new Jbutton(), button -> {
     *     button.setLabel("按钮文本");
     *     button.setSize(200,200);
     *     button.setLocation(0,0);
     * });
     * @param name  //组件名称
     * @param jComponent    //添加的组件
     * @param constrains    //组件约束
     * @param consumer      //组件配置
     * @param <T>   //泛型为JComponent
     */
    protected final <T extends JComponent> void addComponent(String name, T jComponent, Object constrains, Consumer<T> consumer){
        if(consumer != null){
            consumer.accept(jComponent);
        }
        this.componentMap.put(name, jComponent);
        this.add(jComponent, constrains);
    }
    /**
     * 为指定容器添加组件
     * @param target    //指定容器
     * @param name  //组件名称
     * @param jComponent    //添加的组件
     * @param consumer      //组件配置
     * @param <T>   //泛型为JComponent
     */
    protected final <T extends JComponent,R extends JComponent> void addComponent(R target, String name, T jComponent, Consumer<T> consumer){
        if(consumer != null){
            consumer.accept(jComponent);
        }
        this.componentMap.put(name, jComponent);
        target.add(jComponent);
    }
    /**
     * 为指定容器添加组件(带组件约束)
     * @param target    //目标组件
     * @param name  //组件名称
     * @param jComponent    //添加的组件
     * @param constrains    //组件约束
     * @param consumer      //组件配置
     * @param <T>   //泛型为JComponent
     */
    protected final <T extends JComponent,R extends JComponent> void addComponent(R target, String name, T jComponent, Object constrains, Consumer<T> consumer){
        if(consumer != null){
            consumer.accept(jComponent);
        }
        this.componentMap.put(name, jComponent);
        target.add(jComponent);
    }
    /**
     * 获取当前存入的组件列表
     * @return
     */
    public Map<String, JComponent> getComponentMap() {
        return componentMap;
    }
}
