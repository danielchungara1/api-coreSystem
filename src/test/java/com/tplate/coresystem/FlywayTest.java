package com.tplate.coresystem;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public class FlywayTest extends TestContainers{
    @Test
    public void runMigrations() {
        // Migrations are running in order to detect if any fails.
    }
}
