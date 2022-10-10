--
-- PostgreSQL database dump
--

-- Dumped from database version 14.5 (Debian 14.5-1.pgdg110+1)
-- Dumped by pg_dump version 14.4

-- Started on 2022-10-10 15:36:47

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3389 (class 0 OID 16421)
-- Dependencies: 223
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.users (id, avatar_url, first_name, last_name, password, email) OVERRIDING SYSTEM VALUE VALUES (2, 'dsadasda', 'first', 'dsadsdasdsadsaa', '132dasddssds', 'Dsaa');
INSERT INTO public.users (id, avatar_url, first_name, last_name, password, email) OVERRIDING SYSTEM VALUE VALUES (3, '', 'bohdan', 'bohdan', 'bohdan', 'bohdan@gmail.com');
INSERT INTO public.users (id, avatar_url, first_name, last_name, password, email) OVERRIDING SYSTEM VALUE VALUES (4, '', 'artur', 'artur', 'artur', 'artur@gmail.com');
INSERT INTO public.users (id, avatar_url, first_name, last_name, password, email) OVERRIDING SYSTEM VALUE VALUES (5, '', 'maksym', 'maksym', 'maksym', 'maksym@gmail.com');
INSERT INTO public.users (id, avatar_url, first_name, last_name, password, email) OVERRIDING SYSTEM VALUE VALUES (6, '', 'roman', 'roman', 'roman', 'roman@gmail.com');


--
-- TOC entry 3382 (class 0 OID 16404)
-- Dependencies: 216
-- Data for Name: posts; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.posts (id, created_at, user_id, description, title, image_url) OVERRIDING SYSTEM VALUE VALUES (8, '2021-02-09 03:00:00', 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore etdolore magna aliqua', 'Моя мрія - відвідати Париж цього року', '');
INSERT INTO public.posts (id, created_at, user_id, description, title, image_url) OVERRIDING SYSTEM VALUE VALUES (9, '2022-02-09 03:00:00', 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore etdolore magna aliqua', 'Захоплюючі фотографії навколо Європи', '');
INSERT INTO public.posts (id, created_at, user_id, description, title, image_url) OVERRIDING SYSTEM VALUE VALUES (10, '2021-05-09 04:00:00', 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore etdolore magna aliqua', 'Про що варто знати, вивчаючи технології', '');
INSERT INTO public.posts (id, created_at, user_id, description, title, image_url) OVERRIDING SYSTEM VALUE VALUES (11, '2021-09-09 04:00:00', 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore etdolore magna aliqua', 'Інстраграм блогери мають нові можливості', '');
INSERT INTO public.posts (id, created_at, user_id, description, title, image_url) OVERRIDING SYSTEM VALUE VALUES (12, '2022-09-09 04:00:00', 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore etdolore magna aliqua', 'Що треба знати, коли їдеш в Німеччину', '');
INSERT INTO public.posts (id, created_at, user_id, description, title, image_url) OVERRIDING SYSTEM VALUE VALUES (13, '2022-04-09 04:00:00', 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore etdolore magna aliqua', '7 наймодніших стильових прийомів восени', '');
INSERT INTO public.posts (id, created_at, user_id, description, title, image_url) OVERRIDING SYSTEM VALUE VALUES (14, '2022-01-29 03:00:00', 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore etdolore magna aliqua', 'Нова колекція NFT вже доступна', '');
INSERT INTO public.posts (id, created_at, user_id, description, title, image_url) OVERRIDING SYSTEM VALUE VALUES (17, '2021-01-29 03:00:00', 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore etdolore magna aliqua', 'Нові лаурети Нобелівської премії 2022', '');


--
-- TOC entry 3376 (class 0 OID 16388)
-- Dependencies: 210
-- Data for Name: comments; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3378 (class 0 OID 16394)
-- Dependencies: 212
-- Data for Name: likes; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.likes (id, user_id) OVERRIDING SYSTEM VALUE VALUES (1, 3);
INSERT INTO public.likes (id, user_id) OVERRIDING SYSTEM VALUE VALUES (2, 4);
INSERT INTO public.likes (id, user_id) OVERRIDING SYSTEM VALUE VALUES (3, 5);
INSERT INTO public.likes (id, user_id) OVERRIDING SYSTEM VALUE VALUES (4, 6);


--
-- TOC entry 3375 (class 0 OID 16385)
-- Dependencies: 209
-- Data for Name: comment_likes; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3380 (class 0 OID 16398)
-- Dependencies: 214
-- Data for Name: post_likes; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.post_likes (post_id, like_id) VALUES (11, 2);
INSERT INTO public.post_likes (post_id, like_id) VALUES (11, 1);
INSERT INTO public.post_likes (post_id, like_id) VALUES (12, 3);
INSERT INTO public.post_likes (post_id, like_id) VALUES (13, 4);


--
-- TOC entry 3386 (class 0 OID 16414)
-- Dependencies: 220
-- Data for Name: tags; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.tags (id, name) OVERRIDING SYSTEM VALUE VALUES (1, 'adventure');
INSERT INTO public.tags (id, name) OVERRIDING SYSTEM VALUE VALUES (2, 'travelling');
INSERT INTO public.tags (id, name) OVERRIDING SYSTEM VALUE VALUES (3, 'fashion');
INSERT INTO public.tags (id, name) OVERRIDING SYSTEM VALUE VALUES (4, 'technology');
INSERT INTO public.tags (id, name) OVERRIDING SYSTEM VALUE VALUES (5, 'science');


--
-- TOC entry 3381 (class 0 OID 16401)
-- Dependencies: 215
-- Data for Name: post_tags; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.post_tags (post_id, tag_id) VALUES (8, 1);
INSERT INTO public.post_tags (post_id, tag_id) VALUES (9, 2);
INSERT INTO public.post_tags (post_id, tag_id) VALUES (10, 4);
INSERT INTO public.post_tags (post_id, tag_id) VALUES (11, 3);
INSERT INTO public.post_tags (post_id, tag_id) VALUES (12, 1);
INSERT INTO public.post_tags (post_id, tag_id) VALUES (13, 3);
INSERT INTO public.post_tags (post_id, tag_id) VALUES (14, 4);
INSERT INTO public.post_tags (post_id, tag_id) VALUES (17, 5);


--
-- TOC entry 3384 (class 0 OID 16410)
-- Dependencies: 218
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.roles (id, name) OVERRIDING SYSTEM VALUE VALUES (1, 'Guest');
INSERT INTO public.roles (id, name) OVERRIDING SYSTEM VALUE VALUES (2, 'Admin');
INSERT INTO public.roles (id, name) OVERRIDING SYSTEM VALUE VALUES (3, 'Owner');


--
-- TOC entry 3388 (class 0 OID 16418)
-- Dependencies: 222
-- Data for Name: user_roles; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.user_roles (role_id, user_id) VALUES (1, 3);
INSERT INTO public.user_roles (role_id, user_id) VALUES (1, 4);
INSERT INTO public.user_roles (role_id, user_id) VALUES (1, 5);
INSERT INTO public.user_roles (role_id, user_id) VALUES (1, 6);


--
-- TOC entry 3396 (class 0 OID 0)
-- Dependencies: 211
-- Name: comments_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.comments_id_seq', 1, false);


--
-- TOC entry 3397 (class 0 OID 0)
-- Dependencies: 213
-- Name: likes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.likes_id_seq', 4, true);


--
-- TOC entry 3398 (class 0 OID 0)
-- Dependencies: 217
-- Name: posts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.posts_id_seq', 17, true);


--
-- TOC entry 3399 (class 0 OID 0)
-- Dependencies: 219
-- Name: roles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.roles_id_seq', 3, true);


--
-- TOC entry 3400 (class 0 OID 0)
-- Dependencies: 221
-- Name: tags_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.tags_id_seq', 5, true);


--
-- TOC entry 3401 (class 0 OID 0)
-- Dependencies: 224
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.users_id_seq', 6, true);


-- Completed on 2022-10-10 15:36:47

--
-- PostgreSQL database dump complete
--

