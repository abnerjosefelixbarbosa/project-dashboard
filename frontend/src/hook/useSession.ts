import { useEffect, useState } from 'react';
import { supabase } from '../service/subabase';
import { Session } from '@supabase/supabase-js';

export function useSession() {
  const [session, setSession] = useState<Session>();

  useEffect(() => {
    supabase.auth.getSession().then(({ data: { session } }) => {
      setSession(session!);
    });

    const {
      data: { subscription },
    } = supabase.auth.onAuthStateChange((_event, session) => {
      setSession(session!);
    });

    return () => subscription.unsubscribe();
  }, []);

  return { session, logout: () => supabase.auth.signOut() };
}