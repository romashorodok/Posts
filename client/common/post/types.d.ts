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
