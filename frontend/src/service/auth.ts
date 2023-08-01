import { supabase } from "./subabase";

export async function authentication() {
    let isAuthenticated = false;    
    const { data } = await supabase.auth.getSession();
    if (data.session) {
        isAuthenticated = true
    }
    return isAuthenticated
}

export async function getUser() {
    const { data } = await supabase.auth.getUser();
    return data.user
}

export async function signout() {
    return await supabase.auth.signOut().then(() => true);
}