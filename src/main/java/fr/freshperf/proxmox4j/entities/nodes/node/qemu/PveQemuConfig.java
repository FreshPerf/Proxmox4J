package fr.freshperf.proxmox4j.entities.nodes.node.qemu;

import com.google.gson.annotations.SerializedName;

public class PveQemuConfig {

    private String name, ostype, bootdisk, boot, agent, smbios1, vmgenid;
    private int sockets, cores, numa, vcpus, memory, balloon;
    private String cpu, machine, bios, vga;
    private String[] args;
    private String digest, description, tags;
    
    // Network interfaces
    private String net0, net1, net2, net3, net4, net5;
    
    // Disks
    private String scsi0, scsi1, scsi2, scsi3;
    private String virtio0, virtio1, virtio2, virtio3;
    private String ide0, ide1, ide2, ide3;
    private String sata0, sata1, sata2, sata3;
    
    // SCSI controller
    private String scsihw;
    
    // Other options
    private int hotplug, onboot, protection, tablet, kvm;

    public String getName() {
        return name;
    }

    public String getOstype() {
        return ostype;
    }

    public String getBootdisk() {
        return bootdisk;
    }

    public String getBoot() {
        return boot;
    }

    public String getAgent() {
        return agent;
    }

    public String getSmbios1() {
        return smbios1;
    }

    public String getVmgenid() {
        return vmgenid;
    }

    public int getSockets() {
        return sockets;
    }

    public int getCores() {
        return cores;
    }

    public int getNuma() {
        return numa;
    }

    public int getVcpus() {
        return vcpus;
    }

    public int getMemory() {
        return memory;
    }

    public int getBalloon() {
        return balloon;
    }

    public String getCpu() {
        return cpu;
    }

    public String getMachine() {
        return machine;
    }

    public String getBios() {
        return bios;
    }

    public String getVga() {
        return vga;
    }

    public String[] getArgs() {
        return args;
    }

    public String getDigest() {
        return digest;
    }

    public String getDescription() {
        return description;
    }

    public String getTags() {
        return tags;
    }

    public String getNet0() {
        return net0;
    }

    public String getNet1() {
        return net1;
    }

    public String getNet2() {
        return net2;
    }

    public String getNet3() {
        return net3;
    }

    public String getNet4() {
        return net4;
    }

    public String getNet5() {
        return net5;
    }

    public String getScsi0() {
        return scsi0;
    }

    public String getScsi1() {
        return scsi1;
    }

    public String getScsi2() {
        return scsi2;
    }

    public String getScsi3() {
        return scsi3;
    }

    public String getVirtio0() {
        return virtio0;
    }

    public String getVirtio1() {
        return virtio1;
    }

    public String getVirtio2() {
        return virtio2;
    }

    public String getVirtio3() {
        return virtio3;
    }

    public String getIde0() {
        return ide0;
    }

    public String getIde1() {
        return ide1;
    }

    public String getIde2() {
        return ide2;
    }

    public String getIde3() {
        return ide3;
    }

    public String getSata0() {
        return sata0;
    }

    public String getSata1() {
        return sata1;
    }

    public String getSata2() {
        return sata2;
    }

    public String getSata3() {
        return sata3;
    }

    public String getScsihw() {
        return scsihw;
    }

    public int getHotplug() {
        return hotplug;
    }

    public int getOnboot() {
        return onboot;
    }

    public int getProtection() {
        return protection;
    }

    public int getTablet() {
        return tablet;
    }

    public int getKvm() {
        return kvm;
    }

    @Override
    public String toString() {
        return "PveQemuConfig{" +
                "name='" + name + '\'' +
                ", ostype='" + ostype + '\'' +
                ", sockets=" + sockets +
                ", cores=" + cores +
                ", memory=" + memory +
                ", boot='" + boot + '\'' +
                ", machine='" + machine + '\'' +
                '}';
    }
}

