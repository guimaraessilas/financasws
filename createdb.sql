﻿create table usuario(
	id_usuario serial primary key,
	nome text not null,
	email text not null,
	senha text not null,
	dt_cadastro timestamp default current_date,
	dt_alteracao timestamp,
	dt_nascimento timestamp
);

create table lembrete(
	id_lembrete serial primary key,
	id_origem numeric,
	titulo text not null,
	descricao text,
	dt_lembrete timestamp not null,
	dt_cadastro timestamp default current_date,
	dt_alteracao timestamp,
	tipo_origem text,
	fixo boolean default false
);

create table receita(
	id_receita serial primary key,
	id_contato numeric,
	id_usuario numeric,
	id_categoria numeric,
	titulo text not null,
	descricao text,
	dt_cadastro timestamp default current_date,
	dt_alteracao timestamp,
	dt_vencimento timestamp,
	valor numeric(9,2),
	fixo boolean default false,
	pago boolean default false,	
	parcelado boolean default false
);

create table despesa(
	id_despesa serial primary key,
	id_contato numeric,
	id_usuario numeric,
	id_categoria numeric,
	titulo text not null,
	descricao text,
	dt_cadastro timestamp default current_date,
	dt_alteracao timestamp,
	dt_vencimento timestamp,
	valor numeric(9,2),
	fixo boolean default false,
	pago boolean default false,
	parcelado boolean default false
);

create table meta(
	id_meta serial primary key,
	id_usuario numeric,
	titulo text,
	descricao text,
	valor numeric(9,2),
	alcancado numeric(9,2),
	prazo timestamp,
	fixo boolean default false,
	pago boolean default false,
	parcelado boolean default false
);

create table parcela(
	id_parcela serial primary key,
	id_origem numeric,
	dt_cadastro timestamp default current_date,
	dt_alteracao timestamp,
	dt_vencimento timestamp,
	numero_parcelas numeric,
	periodo numeric,
	valor_entrada numeric(9,2),
	valor_total numeric(9,2),
	valor_parcela numeric(9,2),
	tipo_origem text
);

create table contato(
	id_contato serial primary key,
	nome text,
	email text,
	telefone text,
	dt_cadastro timestamp default current_date,
	dt_alteracao timestamp
);

create table categoria(
	id_categoria serial primary key,
	titulo text,
	descricao text,
	dt_cadastro timestamp default current_date,
	dt_alteracao timestamp
);
