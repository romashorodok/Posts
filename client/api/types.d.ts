type CookiesType = {
  set(name, value, opts?): CookiesType;
  get(name, opts?): string?;
};

type Credentials = {
  email: string;
  password: string;
};

type Authentication = {
  accessToken: string;
  username: string;
};
