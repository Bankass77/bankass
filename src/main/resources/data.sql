#INSERT INTO bureau.`role`(role_id, name, `role`) VALUES("1", "Administrateur", "ADMIN");
#INSERT INTO bureau.`role` (role_id, name,`role`) VALUES("2", "Useur", "USER");
#INSERT INTO bureau.`user` (user_id, is_login, email, name, password) VALUES("1", False, "admin@admin.com", "Abdoulaye", "Abdoulaye79!");
#INSERT INTO bureau.user_role (user_id, role_id) VALUES("1", "1");
#INSERT INTO bureau.`language` (language_id, language_name, language_code, country_code, is_default) VALUES("1", "Français", "Fr", "ML", true);
#INSERT INTO bureau.`language` (language_id, language_name, language_code, country_code, is_default) VALUES("2", "Anglais", "En", "US", false);