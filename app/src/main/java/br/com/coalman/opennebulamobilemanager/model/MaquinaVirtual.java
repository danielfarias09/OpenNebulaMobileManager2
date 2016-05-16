package br.com.coalman.opennebulamobilemanager.model;

import java.io.Serializable;

public class MaquinaVirtual  implements Serializable{
    private String id;
    private String nome;
    private String status;
    private String ip;
    private String mac;
    private String memoria;
    private String vcpu;
    private String disco;
    private int idImagem;

    public MaquinaVirtual(String id, String nome, String status, String ip, String mac, String memoria, String vcpu,String disco,int idImagem ) {
        super();
        this.id = id;
        this.nome = nome;
        this.status = status;
        this.ip = ip;
        this.mac = mac;
        this.memoria = memoria;
        this.vcpu = vcpu;
        this.disco = disco;
        this.idImagem = idImagem;
    }

    public MaquinaVirtual(){}


    @Override
    public String toString(){
        return nome;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }


    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getMemoria() {
        return memoria;
    }

    public void setMemoria(String memoria) {
        this.memoria = memoria;
    }

    public String getVcpu() {
        return vcpu;
    }

    public void setVcpu(String vcpu) {
        this.vcpu = vcpu;
    }

    public String getDisco() {
        return disco;
    }

    public void setDisco(String disco) {
        this.disco = disco;
    }

    public int getIdImagem() {
        return idImagem;
    }

    public void setIdImagem(int idImagem) {
        this.idImagem = idImagem;
    }
}
