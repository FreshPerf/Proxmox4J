package fr.freshperf.proxmox4j.throwable;

public class ProxmoxAPIError extends Exception{

    public ProxmoxAPIError(Throwable e){
        super(e);
    }

    public ProxmoxAPIError(String message){
        super(message);
    }

    public ProxmoxAPIError(String message, Throwable e){
        super(message, e);
    }
}
