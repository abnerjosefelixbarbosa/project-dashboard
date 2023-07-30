import { Session } from "@supabase/supabase-js";
import { supabase } from "./subabase";

interface SessionI {
  data?: Session | null;
}

export function auth() {
  const sessionI: SessionI = {};

  supabase.auth.getSession().then(({ data: { session } }) => {
    sessionI.data = session;
  });

  supabase.auth.onAuthStateChange((_event, session) => {
    sessionI.data = session;
  });

  if (!sessionI.data?.user) {
    return false
  }

  return true;
}
