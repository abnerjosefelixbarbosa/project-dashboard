import { supabase } from "./subabase";

export async function getSession() {
  const { data  } = await supabase.auth.getSession();
  return data.session;
}

export async function getUser() {
  const { data } = await supabase.auth.getUser();
  return data.user;
}

export async function signOut() {
  return await supabase.auth.signOut().then(() => true);
}
