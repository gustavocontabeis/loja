package br.com.coder.loja.entity;

import javax.persistence.Transient;



public abstract class AbstractBaseEntity implements BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Transient
	private Boolean selecionado;
	
	@Override
	public int hashCode() {
		return (getId() == null) ? 0 : getId().intValue();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

	public Boolean getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Boolean selecionado) {
		this.selecionado = selecionado;
	}

}
