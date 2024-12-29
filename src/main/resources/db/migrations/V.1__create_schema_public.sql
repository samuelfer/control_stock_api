
CREATE SCHEMA IF NOT EXISTS public;


ALTER SCHEMA public OWNER TO postgres;

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;


CREATE TABLE public.categoria (
    id bigint NOT NULL,
    created_by character varying(255),
    created_date timestamp(6) without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp(6) without time zone,
    descricao text,
    nome character varying(75) NOT NULL
);


ALTER TABLE public.categoria OWNER TO postgres;

CREATE SEQUENCE public.categoria_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.categoria_seq OWNER TO postgres;

CREATE TABLE public.cliente (
    id bigint NOT NULL,
    created_by character varying(255),
    created_date timestamp(6) without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp(6) without time zone,
    email character varying(50),
    nome character varying(50)
);


ALTER TABLE public.cliente OWNER TO postgres;

CREATE SEQUENCE public.cliente_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cliente_seq OWNER TO postgres;

CREATE TABLE public.fornecedor (
    id bigint NOT NULL,
    created_by character varying(255),
    created_date timestamp(6) without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp(6) without time zone,
    cnpj character varying(255),
    email character varying(255),
    endereco character varying(255),
    nome character varying(255),
    telefone character varying(255)
);


ALTER TABLE public.fornecedor OWNER TO postgres;

CREATE SEQUENCE public.fornecedor_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fornecedor_seq OWNER TO postgres;

CREATE TABLE public.item (
    id bigint NOT NULL,
    created_by character varying(255),
    created_date timestamp(6) without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp(6) without time zone,
    desconto real,
    descricao character varying(255),
    preco real,
    quantidade smallint,
    quantidade_minina smallint,
    unidade_venda character varying(100),
    fornecedor_id bigint,
    marca_id bigint,
    produto_id bigint
);


ALTER TABLE public.item OWNER TO postgres;


CREATE SEQUENCE public.item_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.item_seq OWNER TO postgres;


CREATE TABLE public.login_request (
    id bigint NOT NULL,
    password character varying(255),
    username character varying(255)
);


ALTER TABLE public.login_request OWNER TO postgres;


ALTER TABLE public.login_request ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.login_request_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


CREATE TABLE public.marca (
    id bigint NOT NULL,
    created_by character varying(255),
    created_date timestamp(6) without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp(6) without time zone,
    created_at timestamp(6) without time zone NOT NULL,
    descricao text,
    nome character varying(75) NOT NULL,
    updated_at timestamp(6) without time zone
);


ALTER TABLE public.marca OWNER TO postgres;


CREATE SEQUENCE public.marca_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.marca_seq OWNER TO postgres;

CREATE TABLE public.ordem (
    id bigint NOT NULL,
    created_by character varying(255),
    created_date timestamp(6) without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp(6) without time zone,
    desconto real NOT NULL,
    descricao character varying(255),
    item_desconto real NOT NULL,
    promocao character varying(50),
    status integer NOT NULL,
    sub_total real NOT NULL,
    taxa real NOT NULL,
    total real NOT NULL,
    cliente_id bigint,
    fornecedor_id bigint
);


ALTER TABLE public.ordem OWNER TO postgres;


CREATE TABLE public.ordem_item (
    id bigint NOT NULL,
    created_by character varying(255),
    created_date timestamp(6) without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp(6) without time zone,
    desconto real NOT NULL,
    descricao text,
    quantidade smallint NOT NULL,
    item_id bigint,
    order_id bigint
);


ALTER TABLE public.ordem_item OWNER TO postgres;


CREATE SEQUENCE public.ordem_item_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ordem_item_seq OWNER TO postgres;


CREATE SEQUENCE public.ordem_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ordem_seq OWNER TO postgres;


CREATE TABLE public.privilegio (
    id bigint NOT NULL,
    created_by character varying(255),
    created_date timestamp(6) without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp(6) without time zone,
    descricao character varying(255),
    role_id bigint
);


ALTER TABLE public.privilegio OWNER TO postgres;


CREATE SEQUENCE public.privilegio_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.privilegio_seq OWNER TO postgres;


CREATE TABLE public.produto (
    id bigint NOT NULL,
    created_by character varying(255),
    created_date timestamp(6) without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp(6) without time zone,
    descricao text,
    nome character varying(75) NOT NULL,
    categoria_id bigint,
    subcategoria_id bigint
);


ALTER TABLE public.produto OWNER TO postgres;

CREATE SEQUENCE public.produto_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.produto_seq OWNER TO postgres;


CREATE TABLE public.role (
    id bigint NOT NULL,
    created_by character varying(255),
    created_date timestamp(6) without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp(6) without time zone,
    descricao character varying(255),
    detalhes character varying(255)
);


ALTER TABLE public.role OWNER TO postgres;


CREATE SEQUENCE public.role_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.role_seq OWNER TO postgres;


CREATE TABLE public.secure_token (
    id bigint NOT NULL,
    expired_at timestamp(6) without time zone NOT NULL,
    token character varying(255),
    usuario_id bigint
);


ALTER TABLE public.secure_token OWNER TO postgres;


CREATE SEQUENCE public.secure_token_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.secure_token_seq OWNER TO postgres;


CREATE TABLE public.situacao (
    id integer NOT NULL,
    created_by character varying(255),
    created_date timestamp(6) without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp(6) without time zone,
    nome character varying(255)
);


ALTER TABLE public.situacao OWNER TO postgres;


CREATE SEQUENCE public.situacao_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.situacao_seq OWNER TO postgres;


CREATE TABLE public.sub_categoria (
    id bigint NOT NULL,
    created_by character varying(255),
    created_date timestamp(6) without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp(6) without time zone,
    description character varying(255),
    categoria_id bigint
);


ALTER TABLE public.sub_categoria OWNER TO postgres;


CREATE SEQUENCE public.sub_categoria_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sub_categoria_seq OWNER TO postgres;


CREATE TABLE public.usuario (
    id bigint NOT NULL,
    created_by character varying(255),
    created_date timestamp(6) without time zone,
    last_modified_by character varying(255),
    last_modified_date timestamp(6) without time zone,
    account_verified boolean NOT NULL,
    data_nascimento timestamp(6) without time zone,
    email character varying(50) NOT NULL,
    imagem_url character varying(255),
    login character varying(50) NOT NULL,
    login_disabled boolean NOT NULL,
    nome character varying(50) NOT NULL,
    password character varying(15) NOT NULL,
    password_hash character varying(255),
    telefone character varying(255),
    ultimo_login timestamp(6) without time zone
);


ALTER TABLE public.usuario OWNER TO postgres;


CREATE TABLE public.usuario_privilegio (
    id bigint NOT NULL,
    privilegio_id bigint,
    usuario_id bigint,
    privilegio bigint,
    usuario bigint
);


ALTER TABLE public.usuario_privilegio OWNER TO postgres;


CREATE SEQUENCE public.usuario_privilegio_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_privilegio_seq OWNER TO postgres;


CREATE SEQUENCE public.usuario_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_seq OWNER TO postgres;


SELECT pg_catalog.setval('public.cliente_seq', 1, false);
SELECT pg_catalog.setval('public.fornecedor_seq', 1, false);
SELECT pg_catalog.setval('public.item_seq', 1, false);
SELECT pg_catalog.setval('public.login_request_id_seq', 1, false);
SELECT pg_catalog.setval('public.marca_seq', 1, false);
SELECT pg_catalog.setval('public.ordem_item_seq', 1, false);
SELECT pg_catalog.setval('public.ordem_seq', 1, false);
SELECT pg_catalog.setval('public.privilegio_seq', 1, false);
SELECT pg_catalog.setval('public.produto_seq', 1, false);
SELECT pg_catalog.setval('public.role_seq', 1, false);
SELECT pg_catalog.setval('public.secure_token_seq', 1, false);
SELECT pg_catalog.setval('public.situacao_seq', 1, false);
SELECT pg_catalog.setval('public.sub_categoria_seq', 1, false);
SELECT pg_catalog.setval('public.usuario_privilegio_seq', 1, false);
SELECT pg_catalog.setval('public.usuario_seq', 1, false);


ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.fornecedor
    ADD CONSTRAINT fornecedor_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.item
    ADD CONSTRAINT item_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.login_request
    ADD CONSTRAINT login_request_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.marca
    ADD CONSTRAINT marca_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.ordem_item
    ADD CONSTRAINT ordem_item_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.ordem
    ADD CONSTRAINT ordem_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.privilegio
    ADD CONSTRAINT privilegio_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.produto
    ADD CONSTRAINT produto_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.secure_token
    ADD CONSTRAINT secure_token_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.situacao
    ADD CONSTRAINT situacao_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.sub_categoria
    ADD CONSTRAINT sub_categoria_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.secure_token
    ADD CONSTRAINT secure_token_unique UNIQUE (token);

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_email_unique UNIQUE (email);


ALTER TABLE ONLY public.usuario_privilegio
    ADD CONSTRAINT usuario_privilegio_fk UNIQUE (usuario_id, privilegio_id);


ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.usuario_privilegio
    ADD CONSTRAINT usuario_privilegio_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.usuario_privilegio
    ADD CONSTRAINT usuario_fk FOREIGN KEY (usuario) REFERENCES public.usuario(id);


ALTER TABLE ONLY public.ordem
    ADD CONSTRAINT ordem_cliente_fk FOREIGN KEY (cliente_id) REFERENCES public.cliente(id);


ALTER TABLE ONLY public.ordem_item
    ADD CONSTRAINT ordem_item_fk FOREIGN KEY (item_id) REFERENCES public.item(id);


ALTER TABLE ONLY public.produto
    ADD CONSTRAINT produto_subcategoria_fk FOREIGN KEY (subcategoria_id) REFERENCES public.sub_categoria(id);


ALTER TABLE ONLY public.privilegio
    ADD CONSTRAINT priviliegio_role_fk FOREIGN KEY (role_id) REFERENCES public.role(id);

ALTER TABLE ONLY public.ordem_item
    ADD CONSTRAINT ordem_item_ordem_fk FOREIGN KEY (order_id) REFERENCES public.ordem(id);


ALTER TABLE ONLY public.ordem
    ADD CONSTRAINT ordem_fornecedor_fk FOREIGN KEY (fornecedor_id) REFERENCES public.fornecedor(id);


ALTER TABLE ONLY public.usuario_privilegio
    ADD CONSTRAINT usuario_privilegio_privilegio_fk FOREIGN KEY (privilegio) REFERENCES public.privilegio(id);


ALTER TABLE ONLY public.secure_token
    ADD CONSTRAINT secure_token_usuario_fk FOREIGN KEY (usuario_id) REFERENCES public.usuario(id);


ALTER TABLE ONLY public.produto
    ADD CONSTRAINT produto_categoria_fk FOREIGN KEY (categoria_id) REFERENCES public.categoria(id);


ALTER TABLE ONLY public.item
    ADD CONSTRAINT item_produto_fk FOREIGN KEY (produto_id) REFERENCES public.produto(id);


ALTER TABLE ONLY public.item
    ADD CONSTRAINT item_fornecedor_fk FOREIGN KEY (fornecedor_id) REFERENCES public.fornecedor(id);


ALTER TABLE ONLY public.sub_categoria
    ADD CONSTRAINT sub_categoria_categoria_fk FOREIGN KEY (categoria_id) REFERENCES public.categoria(id);


ALTER TABLE ONLY public.item
    ADD CONSTRAINT item_marca_fk FOREIGN KEY (marca_id) REFERENCES public.marca(id);