-- Check if enabled fields have null values, if true then set true as a default value
do
$$
    declare
        current_record record;
    begin
        for current_record in select id, enabled
                              from permission
            loop
                if current_record.enabled is null then
                    update permission
                    set enabled = true
                    where id = current_record.id;
                end if;
            end loop;
    end;
$$;

-- By business rule a permission has 2 possibles values true or false.
ALTER TABLE permission
    ALTER COLUMN enabled SET NOT NULL;