import { supabase } from "./subabase";

interface User {
  email: string;
  password: string;
  full_name: string;
}

export async function signInGithub() {
  const { error } = await supabase.auth.signInWithOAuth({ provider: "github" });
  if (error) throw error;
}

export async function signUp({ email, password, full_name }: User) {
  const { error, data } = await supabase.auth.signUp({
    email: email,
    password: password,
    options: {
      data: {
        full_name: full_name,
      },
    },
  });
  if (error) throw error;
  console.log(data);
}

export async function authentication() {
  const { data, error } = await supabase.auth.getSession();
  if (error) throw error;
  return data.session?.user;
}

export async function getUser() {
  const { data, error } = await supabase.auth.getUser();
  if (error) throw error;
  return data.user;
}

export async function signout() {
  return await supabase.auth.signOut().then(() => true);
}
