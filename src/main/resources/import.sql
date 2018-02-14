INSERT INTO public.perfil(id_perfil, nome) VALUES (nextval('perfil_seq'), 'ADM');
INSERT INTO public.usuario(id_usuario, email, login, nome, senha) VALUES (nextval('usuario_seq'), 'gustavo@codersistemas.com.br', 'gustavo', 'Gustavo da Silva', '123');
INSERT INTO public.usuario_perfil(id_usuario, id_perfil) VALUES (1, 1);