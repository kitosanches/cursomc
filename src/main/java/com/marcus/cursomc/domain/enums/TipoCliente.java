package com.marcus.cursomc.domain.enums;

public enum TipoCliente {
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
private int id;
private String descricao;
private TipoCliente(int id, String descricao) {
	this.id = id;
	this.descricao = descricao;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDescricao() {
	return descricao;
}
public void setDescricao(String descricao) {
	this.descricao = descricao;
}
public static TipoCliente toEnum(Integer cod) {
	if(cod == null) {
		return null;
	}
	for(TipoCliente x: TipoCliente.values()) {
		if(cod.equals(x.getId())) {
			return x;
		}
	}
	throw new IllegalArgumentException("Id invalido" + cod);
}

}
