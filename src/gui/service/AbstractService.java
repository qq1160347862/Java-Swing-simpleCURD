package gui.service;

import gui.AbstractFrame;

public abstract class AbstractService {

    protected AbstractFrame<? extends AbstractService> abstractFrame;

    public final void setFrame(AbstractFrame<? extends AbstractService> abstractFrame){
        System.out.println("Frame has bind");
        this.abstractFrame = abstractFrame;
    }
}
