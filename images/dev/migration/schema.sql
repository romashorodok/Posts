--
-- PostgreSQL database dump
--

-- Dumped from database version 14.5 (Debian 14.5-1.pgdg110+1)
-- Dumped by pg_dump version 14.4

-- Started on 2022-09-26 18:52:43 EEST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3374 (class 1262 OID 16384)
-- Name: posts-db; Type: DATABASE; Schema: -; Owner: root
--

ALTER DATABASE "posts-db" OWNER TO root;

\connect -reuse-previous=on "dbname='posts-db'"

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 209 (class 1259 OID 16385)
-- Name: comment_likes; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.comment_likes (
    like_id integer NOT NULL,
    comment_id integer NOT NULL
);


ALTER TABLE public.comment_likes OWNER TO root;

--
-- TOC entry 210 (class 1259 OID 16388)
-- Name: comments; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.comments (
    id integer NOT NULL,
    user_id integer NOT NULL,
    content character varying(1000) NOT NULL,
    created_at timestamp without time zone NOT NULL,
    post_id integer NOT NULL
);


ALTER TABLE public.comments OWNER TO root;

--
-- TOC entry 211 (class 1259 OID 16393)
-- Name: likes; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.likes (
    id integer NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE public.likes OWNER TO root;

--
-- TOC entry 212 (class 1259 OID 16396)
-- Name: post_likes; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.post_likes (
    post_id integer NOT NULL,
    like_id integer NOT NULL
);


ALTER TABLE public.post_likes OWNER TO root;

--
-- TOC entry 213 (class 1259 OID 16399)
-- Name: post_tags; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.post_tags (
    post_id integer NOT NULL,
    tag_id integer NOT NULL
);


ALTER TABLE public.post_tags OWNER TO root;

--
-- TOC entry 214 (class 1259 OID 16402)
-- Name: posts; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.posts (
    title character varying(40)[] NOT NULL,
    id integer NOT NULL,
    description character varying(60)[] NOT NULL,
    created_at timestamp without time zone NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE public.posts OWNER TO root;

--
-- TOC entry 215 (class 1259 OID 16407)
-- Name: roles; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.roles (
    id integer NOT NULL,
    name character varying(40) NOT NULL
);


ALTER TABLE public.roles OWNER TO root;

--
-- TOC entry 216 (class 1259 OID 16410)
-- Name: tags; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.tags (
    id integer NOT NULL,
    name character varying(40) NOT NULL
);


ALTER TABLE public.tags OWNER TO root;

--
-- TOC entry 217 (class 1259 OID 16413)
-- Name: user_roles; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.user_roles (
    role_id integer NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE public.user_roles OWNER TO root;

--
-- TOC entry 218 (class 1259 OID 16416)
-- Name: users; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.users (
    id integer NOT NULL,
    avatar_url character varying,
    "firstName" character varying NOT NULL,
    "lastName" character varying NOT NULL,
    password character varying(25) NOT NULL,
    email character varying(100) NOT NULL
);


ALTER TABLE public.users OWNER TO root;

--
-- TOC entry 3203 (class 2606 OID 16422)
-- Name: comment_likes comment_likes_like_id_key; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.comment_likes
    ADD CONSTRAINT comment_likes_like_id_key UNIQUE (like_id);


--
-- TOC entry 3205 (class 2606 OID 16424)
-- Name: comments comments_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY (id);


--
-- TOC entry 3207 (class 2606 OID 16426)
-- Name: likes likes_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.likes
    ADD CONSTRAINT likes_pkey PRIMARY KEY (id);


--
-- TOC entry 3209 (class 2606 OID 16428)
-- Name: post_likes post_likes_like_id_key; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.post_likes
    ADD CONSTRAINT post_likes_like_id_key UNIQUE (like_id);


--
-- TOC entry 3211 (class 2606 OID 16430)
-- Name: posts posts_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.posts
    ADD CONSTRAINT posts_pkey PRIMARY KEY (id);


--
-- TOC entry 3213 (class 2606 OID 16432)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- TOC entry 3215 (class 2606 OID 16434)
-- Name: tags tags_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.tags
    ADD CONSTRAINT tags_pkey PRIMARY KEY (id);


--
-- TOC entry 3217 (class 2606 OID 16436)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 3218 (class 2606 OID 16437)
-- Name: comment_likes comment_likes_comment_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.comment_likes
    ADD CONSTRAINT comment_likes_comment_id_fkey FOREIGN KEY (comment_id) REFERENCES public.comments(id);


--
-- TOC entry 3219 (class 2606 OID 16442)
-- Name: comment_likes comment_likes_like_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.comment_likes
    ADD CONSTRAINT comment_likes_like_id_fkey FOREIGN KEY (like_id) REFERENCES public.likes(id);


--
-- TOC entry 3220 (class 2606 OID 16447)
-- Name: comments comments_post_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_post_id_fkey FOREIGN KEY (post_id) REFERENCES public.posts(id);


--
-- TOC entry 3221 (class 2606 OID 16452)
-- Name: comments comments_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3222 (class 2606 OID 16457)
-- Name: likes likes_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.likes
    ADD CONSTRAINT likes_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3223 (class 2606 OID 16462)
-- Name: post_likes post_likes_like_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.post_likes
    ADD CONSTRAINT post_likes_like_id_fkey FOREIGN KEY (like_id) REFERENCES public.likes(id);


--
-- TOC entry 3224 (class 2606 OID 16467)
-- Name: post_likes post_likes_post_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.post_likes
    ADD CONSTRAINT post_likes_post_id_fkey FOREIGN KEY (post_id) REFERENCES public.posts(id);


--
-- TOC entry 3225 (class 2606 OID 16472)
-- Name: post_tags post_tags_post_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.post_tags
    ADD CONSTRAINT post_tags_post_id_fkey FOREIGN KEY (post_id) REFERENCES public.posts(id);


--
-- TOC entry 3226 (class 2606 OID 16477)
-- Name: post_tags post_tags_tag_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.post_tags
    ADD CONSTRAINT post_tags_tag_id_fkey FOREIGN KEY (tag_id) REFERENCES public.tags(id);


--
-- TOC entry 3227 (class 2606 OID 16482)
-- Name: posts posts_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.posts
    ADD CONSTRAINT posts_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3228 (class 2606 OID 16487)
-- Name: user_roles user_roles_role_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.roles(id);


--
-- TOC entry 3229 (class 2606 OID 16492)
-- Name: user_roles user_roles_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


-- Completed on 2022-09-26 18:52:44 EEST

--
-- PostgreSQL database dump complete
--

