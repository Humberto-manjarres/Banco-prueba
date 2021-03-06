PGDMP     )    8                y            Banco    13.4    13.4     ?           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            ?           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            ?           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            ?           1262    16394    Banco    DATABASE     k   CREATE DATABASE "Banco" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_United States.1252';
    DROP DATABASE "Banco";
                postgres    false            ?            1259    16395    clientes    TABLE     {   CREATE TABLE public.clientes (
    id integer NOT NULL,
    nombre text NOT NULL,
    direccion text,
    telefono text
);
    DROP TABLE public.clientes;
       public         heap    postgres    false            ?            1259    16416    clientes_id_seq    SEQUENCE     ?   ALTER TABLE public.clientes ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.clientes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    200            ?            1259    16400    cuentas    TABLE     {   CREATE TABLE public.cuentas (
    id bigint NOT NULL,
    numero text NOT NULL,
    saldo numeric,
    idcliente bigint
);
    DROP TABLE public.cuentas;
       public         heap    postgres    false            ?            1259    16446    cuentas_id_seq    SEQUENCE     ?   ALTER TABLE public.cuentas ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.cuentas_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    201            ?            1259    16408 
   movimiento    TABLE     ?   CREATE TABLE public.movimiento (
    id bigint NOT NULL,
    tipo "char",
    fecha date,
    valor numeric,
    idcuenta bigint
);
    DROP TABLE public.movimiento;
       public         heap    postgres    false            ?            1259    16480    movimiento_id_seq    SEQUENCE     ?   ALTER TABLE public.movimiento ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.movimiento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    202            ?          0    16395    clientes 
   TABLE DATA           C   COPY public.clientes (id, nombre, direccion, telefono) FROM stdin;
    public          postgres    false    200   $       ?          0    16400    cuentas 
   TABLE DATA           ?   COPY public.cuentas (id, numero, saldo, idcliente) FROM stdin;
    public          postgres    false    201   A       ?          0    16408 
   movimiento 
   TABLE DATA           F   COPY public.movimiento (id, tipo, fecha, valor, idcuenta) FROM stdin;
    public          postgres    false    202   ^       ?           0    0    clientes_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.clientes_id_seq', 9, true);
          public          postgres    false    203            ?           0    0    cuentas_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.cuentas_id_seq', 8, true);
          public          postgres    false    204            ?           0    0    movimiento_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.movimiento_id_seq', 4, true);
          public          postgres    false    205            2           2606    16399    clientes clientes_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.clientes
    ADD CONSTRAINT clientes_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.clientes DROP CONSTRAINT clientes_pkey;
       public            postgres    false    200            4           2606    16407    cuentas cuentas_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.cuentas
    ADD CONSTRAINT cuentas_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.cuentas DROP CONSTRAINT cuentas_pkey;
       public            postgres    false    201            6           2606    16415    movimiento movimiento_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.movimiento
    ADD CONSTRAINT movimiento_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.movimiento DROP CONSTRAINT movimiento_pkey;
       public            postgres    false    202            7           2606    16470    cuentas fk_cuentas_clientes    FK CONSTRAINT        ALTER TABLE ONLY public.cuentas
    ADD CONSTRAINT fk_cuentas_clientes FOREIGN KEY (idcliente) REFERENCES public.clientes(id);
 E   ALTER TABLE ONLY public.cuentas DROP CONSTRAINT fk_cuentas_clientes;
       public          postgres    false    201    2866    200            8           2606    16475    movimiento fk_movimiento_cuenta    FK CONSTRAINT     ?   ALTER TABLE ONLY public.movimiento
    ADD CONSTRAINT fk_movimiento_cuenta FOREIGN KEY (idcuenta) REFERENCES public.cuentas(id) NOT VALID;
 I   ALTER TABLE ONLY public.movimiento DROP CONSTRAINT fk_movimiento_cuenta;
       public          postgres    false    202    2868    201            ?      x?????? ? ?      ?      x?????? ? ?      ?      x?????? ? ?     