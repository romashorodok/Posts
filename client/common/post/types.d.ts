export type Tag = {
  name: string;
};

export type Post = {
  id?: number;

  title: string;
  createdAt: string;
  description: string;

  image?: string;
  user?: any;
  tags?: Array<Tag>;
};

export type Profile = {
  firstName: string;
  lastName: string;
  email: string;

  posts: {
    content: Array<Post>;
    totalElements: number;
  }
}