import { createClient } from '@supabase/supabase-js';

const SUPABASE_URL = import.meta.env.VITE_SUPABASE_URL ?? "https://btxamhjaugeqpoiwwhbp.supabase.co";
const SUPABASE_KEY = import.meta.env.VITE_SUPABASE_KEY ?? "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImJ0eGFtaGphdWdlcXBvaXd3aGJwIiwicm9sZSI6ImFub24iLCJpYXQiOjE2OTA2NjU0ODIsImV4cCI6MjAwNjI0MTQ4Mn0.5ID3_rDn3SUx-E5xHn7KcCp4Ymexk-U95EsxLlykD6o";

export const supabase = createClient(SUPABASE_URL, SUPABASE_KEY);