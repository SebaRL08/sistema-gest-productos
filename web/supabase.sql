-- Ejecuta este script en el SQL Editor de tu proyecto Supabase.
create table if not exists public.productos (
  codigo text primary key,
  nombre text not null,
  descripcion text default '',
  precio numeric(10,2) not null check (precio >= 0),
  cantidad integer not null check (cantidad >= 0),
  created_at timestamptz not null default now()
);

alter table public.productos enable row level security;

-- Aplicación de demostración académica SIN inicio de sesión.
-- Estas políticas permiten que cualquiera con el enlace público pueda leer,
-- insertar, actualizar y eliminar los productos. No uses datos sensibles.
drop policy if exists "public read productos" on public.productos;
drop policy if exists "public insert productos" on public.productos;
drop policy if exists "public update productos" on public.productos;
drop policy if exists "public delete productos" on public.productos;

create policy "public read productos"
on public.productos for select to anon using (true);

create policy "public insert productos"
on public.productos for insert to anon with check (true);

create policy "public update productos"
on public.productos for update to anon using (true) with check (true);

create policy "public delete productos"
on public.productos for delete to anon using (true);
