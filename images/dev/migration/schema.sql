--
-- PostgreSQL database dump
--

-- Dumped from database version 14.5 (Debian 14.5-1.pgdg110+1)
-- Dumped by pg_dump version 14.4

-- Started on 2022-09-26 16:16:52 EEST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;


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
-- TOC entry 217 (class 1259 OID 24618)
-- Name: comment_likes; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.comment_likes (
    like_id integer NOT NULL,
    comment_id integer NOT NULL
);


ALTER TABLE public.comment_likes OWNER TO root;

--
-- TOC entry 211 (class 1259 OID 24588)
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
-- TOC entry 216 (class 1259 OID 24613)
-- Name: likes; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.likes (
    id integer NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE public.likes OWNER TO root;

--
-- TOC entry 218 (class 1259 OID 24623)
-- Name: post_likes; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.post_likes (
    post_id integer NOT NULL,
    like_id integer NOT NULL
);


ALTER TABLE public.post_likes OWNER TO root;

--
-- TOC entry 213 (class 1259 OID 24602)
-- Name: post_tags; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.post_tags (
    post_id integer NOT NULL,
    tag_id integer NOT NULL
);


ALTER TABLE public.post_tags OWNER TO root;

--
-- TOC entry 209 (class 1259 OID 24576)
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
-- TOC entry 214 (class 1259 OID 24605)
-- Name: roles; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.roles (
    id integer NOT NULL,
    name character varying(40) NOT NULL
);


ALTER TABLE public.roles OWNER TO root;

--
-- TOC entry 210 (class 1259 OID 24583)
-- Name: tags; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.tags (
    id integer NOT NULL,
    name character varying(40) NOT NULL
);


ALTER TABLE public.tags OWNER TO root;

--
-- TOC entry 215 (class 1259 OID 24610)
-- Name: user_roles; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.user_roles (
    role_id integer NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE public.user_roles OWNER TO root;

--
-- TOC entry 212 (class 1259 OID 24595)
-- Name: users; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.users (
    id integer NOT NULL,
    avatar_url character varying,
    "firstName" character varying NOT NULL,
    "lastName" character varying NOT NULL
);


ALTER TABLE public.users OWNER TO root;

--
-- TOC entry 3215 (class 2606 OID 24622)
-- Name: comment_likes comment_likes_like_id_key; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.comment_likes
    ADD CONSTRAINT comment_likes_like_id_key UNIQUE (like_id);


--
-- TOC entry 3207 (class 2606 OID 24594)
-- Name: comments comments_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY (id);


--
-- TOC entry 3213 (class 2606 OID 24617)
-- Name: likes likes_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.likes
    ADD CONSTRAINT likes_pkey PRIMARY KEY (id);


--
-- TOC entry 3217 (class 2606 OID 24627)
-- Name: post_likes post_likes_like_id_key; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.post_likes
    ADD CONSTRAINT post_likes_like_id_key UNIQUE (like_id);


--
-- TOC entry 3203 (class 2606 OID 24582)
-- Name: posts posts_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.posts
    ADD CONSTRAINT posts_pkey PRIMARY KEY (id);


--
-- TOC entry 3211 (class 2606 OID 24609)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- TOC entry 3205 (class 2606 OID 24587)
-- Name: tags tags_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.tags
    ADD CONSTRAINT tags_pkey PRIMARY KEY (id);


--
-- TOC entry 3209 (class 2606 OID 24601)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 3226 (class 2606 OID 24673)
-- Name: comment_likes comment_likes_comment_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.comment_likes
    ADD CONSTRAINT comment_likes_comment_id_fkey FOREIGN KEY (comment_id) REFERENCES public.comments(id);


--
-- TOC entry 3227 (class 2606 OID 24668)
-- Name: comment_likes comment_likes_like_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.comment_likes
    ADD CONSTRAINT comment_likes_like_id_fkey FOREIGN KEY (like_id) REFERENCES public.likes(id);


--
-- TOC entry 3219 (class 2606 OID 24638)
-- Name: comments comments_post_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_post_id_fkey FOREIGN KEY (post_id) REFERENCES public.posts(id);


--
-- TOC entry 3220 (class 2606 OID 24633)
-- Name: comments comments_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3225 (class 2606 OID 24663)
-- Name: likes likes_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.likes
    ADD CONSTRAINT likes_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3229 (class 2606 OID 24678)
-- Name: post_likes post_likes_like_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.post_likes
    ADD CONSTRAINT post_likes_like_id_fkey FOREIGN KEY (like_id) REFERENCES public.likes(id);


--
-- TOC entry 3228 (class 2606 OID 24683)
-- Name: post_likes post_likes_post_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.post_likes
    ADD CONSTRAINT post_likes_post_id_fkey FOREIGN KEY (post_id) REFERENCES public.posts(id);


--
-- TOC entry 3221 (class 2606 OID 24643)
-- Name: post_tags post_tags_post_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.post_tags
    ADD CONSTRAINT post_tags_post_id_fkey FOREIGN KEY (post_id) REFERENCES public.posts(id);


--
-- TOC entry 3222 (class 2606 OID 24648)
-- Name: post_tags post_tags_tag_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.post_tags
    ADD CONSTRAINT post_tags_tag_id_fkey FOREIGN KEY (tag_id) REFERENCES public.tags(id);


--
-- TOC entry 3218 (class 2606 OID 24628)
-- Name: posts posts_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.posts
    ADD CONSTRAINT posts_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3223 (class 2606 OID 24653)
-- Name: user_roles user_roles_role_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.roles(id);


--
-- TOC entry 3224 (class 2606 OID 24658)
-- Name: user_roles user_roles_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


-- Completed on 2022-09-26 16:16:53 EEST

--
-- PostgreSQL database dump complete
--

