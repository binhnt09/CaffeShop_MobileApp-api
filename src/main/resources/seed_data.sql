-- =========================================================================
-- DATABASE RESET & INITIAL SEED DATA FOR SQL SERVER
-- =========================================================================

-- 1. Tạm thời vô hiệu hóa tất cả khóa ngoại để tránh lỗi ràng buộc khi xóa
EXEC sp_MSforeachtable "ALTER TABLE ? NOCHECK CONSTRAINT all"

-- 2. Xóa dữ liệu cũ của toàn bộ các bảng liên quan
DELETE FROM loyalty_transactions;
DELETE FROM order_item_customizations;
DELETE FROM order_items;
DELETE FROM orders;
DELETE FROM branch_menu_items;
DELETE FROM menu_item_customizations;
DELETE FROM customization_options;
DELETE FROM customization_groups;
DELETE FROM menu_items;
DELETE FROM categories;
DELETE FROM branches;
DELETE FROM customers;
DELETE FROM users;
DELETE FROM roles;
DELETE FROM coupons;

-- 3. Reset các cột ID tự tăng (IDENTITY) về 0 để dữ liệu chèn mới bắt đầu từ 1
DBCC CHECKIDENT ('roles', RESEED, 0);
DBCC CHECKIDENT ('users', RESEED, 0);
DBCC CHECKIDENT ('customers', RESEED, 0);
DBCC CHECKIDENT ('branches', RESEED, 0);
DBCC CHECKIDENT ('categories', RESEED, 0);
DBCC CHECKIDENT ('menu_items', RESEED, 0);
DBCC CHECKIDENT ('customization_groups', RESEED, 0);
DBCC CHECKIDENT ('customization_options', RESEED, 0);
DBCC CHECKIDENT ('coupons', RESEED, 0);

-- 4. Bật lại tất cả khóa ngoại sau khi đã xóa và reset sạch sẽ
EXEC sp_MSforeachtable "ALTER TABLE ? CHECK CONSTRAINT all"

-- =========================================================================
-- CHÈN DỮ LIỆU SEED MẪU
-- =========================================================================

-- 1. Insert Roles
INSERT INTO roles (role_name, description) VALUES 
('ADMIN', 'System Administrator'),
('MANAGER', 'Store Manager'),
('CASHIER', 'Front Counter Cashier'),
('BARISTA', 'Drink Bar Barista'),
('CUSTOMER', 'Mobile App Customer');

-- 2. Insert Users (Mật khẩu mặc định: "123456" đã được Bcrypt băm)
INSERT INTO users (full_name, email, phone, password_hash, roleid, status, created_at, updated_at) VALUES
(N'Vũ Quốc Admin', 'admin@coffee.com', '0977777777', '$2a$10$XptD9J6pXWl09t4YkIu2V.O69K0uK2hA2vW2eQ2X6G1mH0q4dD3iH', 1, 'Active', GETDATE(), GETDATE()),
(N'Lê Hoàng Quản Lý', 'manager@coffee.com', '0934567890', '$2a$10$XptD9J6pXWl09t4YkIu2V.O69K0uK2hA2vW2eQ2X6G1mH0q4dD3iH', 2, 'Active', GETDATE(), GETDATE()),
(N'Trần Thị Thu Ngân', 'cashier@coffee.com', '0987654321', '$2a$10$XptD9J6pXWl09t4YkIu2V.O69K0uK2hA2vW2eQ2X6G1mH0q4dD3iH', 3, 'Active', GETDATE(), GETDATE()),
(N'Phạm Văn Pha Chế', 'barista@coffee.com', '0901234567', '$2a$10$XptD9J6pXWl09t4YkIu2V.O69K0uK2hA2vW2eQ2X6G1mH0q4dD3iH', 4, 'Active', GETDATE(), GETDATE()),
(N'Nguyễn Văn Khách', 'customer@coffee.com', '0912345678', '$2a$10$XptD9J6pXWl09t4YkIu2V.O69K0uK2hA2vW2eQ2X6G1mH0q4dD3iH', 5, 'Active', GETDATE(), GETDATE());

-- 3. Insert Customers (Liên kết tài khoản khách hàng)
INSERT INTO customers (userid, loyalty_points, membership_tier) VALUES
(5, 320, 'Bronze');

-- 4. Insert Branches (Chi nhánh)
INSERT INTO branches (branch_name, address, latitude, longitude, opening_time, closing_time, status) VALUES
(N'CaffeShop Hai Bà Trưng', N'120 Hai Bà Trưng, Phường Đa Kao, Quận 1, TP. HCM', 10.782500, 106.697000, '07:00', '22:30', 'Open'),
(N'CaffeShop Nguyễn Đình Chiểu', N'25 Nguyễn Đình Chiểu, Phường Đa Kao, Quận 1, TP. HCM', 10.786500, 106.699500, '07:00', '22:00', 'Open'),
(N'CaffeShop Lê Quý Đôn', N'15 Lê Quý Đôn, Phường Võ Thị Sáu, Quận 3, TP. HCM', 10.778800, 106.692500, '07:00', '23:00', 'Open'),
(N'CaffeShop Phan Xích Long', N'198 Phan Xích Long, Phường 2, Quận Phú Nhuận, TP. HCM', 10.798500, 106.689000, '07:00', '22:30', 'Open');

-- 5. Insert Categories (Danh mục)
INSERT INTO categories (category_name, status, description, image_url) VALUES
(N'Cà Phê', 'Active', N'Cà phê truyền thống Việt Nam và Ý', 'https://images.unsplash.com/photo-1541167760496-1628856ab772?q=80&w=300'),
(N'Trà Trái Cây', 'Active', N'Trà tươi mát kết hợp với các loại quả', 'https://images.unsplash.com/photo-1498804103079-a6351b050096?q=80&w=300'),
(N'Đá Xay', 'Active', N'Đồ uống đá xay mát lạnh, ngọt ngào', 'https://images.unsplash.com/photo-1572490122747-3968b75cc699?q=80&w=300'),
(N'Bánh Ngọt', 'Active', N'Bánh ngọt ăn kèm tuyệt hảo', 'https://images.unsplash.com/photo-1578985545062-69928b1d9587?q=80&w=300');

-- 6. Insert MenuItems (Sản phẩm)
INSERT INTO menu_items (categoryid, sku, item_name, base_price, tax_rate, is_available, description, image_url) VALUES
(1, 'CF-DEN', N'Cà Phê Đen Đá', 29000.00, 10.00, 1, N'Cà phê đen nguyên chất pha phin đậm đà', 'https://images.unsplash.com/photo-1541167760496-1628856ab772?q=80&w=300'),
(1, 'CF-SUA', N'Cà Phê Sữa Đá', 35000.00, 10.00, 1, N'Cà phê phin kết hợp với sữa đặc béo ngậy', 'https://images.unsplash.com/photo-1541167760496-1628856ab772?q=80&w=300'),
(1, 'CF-BAC', N'Bạc Xỉu', 35000.00, 10.00, 1, N'Nhiều sữa ít cà phê ngọt dịu dễ uống', 'https://images.unsplash.com/photo-1541167760496-1628856ab772?q=80&w=300'),
(2, 'TR-DAO', N'Trà Đào Cam Sả', 45000.00, 10.00, 1, N'Trà đào tươi mát kết hợp hương vị cam sả', 'https://images.unsplash.com/photo-1498804103079-a6351b050096?q=80&w=300'),
(2, 'TR-VAI', N'Trà Vải Lài', 45000.00, 10.00, 1, N'Trà lài dịu nhẹ kết hợp với quả vải ngâm ngọt lịm', 'https://images.unsplash.com/photo-1498804103079-a6351b050096?q=80&w=300'),
(3, 'DX-MAT', N'Matcha Đá Xay', 55000.00, 10.00, 1, N'Bột matcha Uji thượng hạng kết hợp kem tươi', 'https://images.unsplash.com/photo-1572490122747-3968b75cc699?q=80&w=300'),
(3, 'DX-SOC', N'Sô-cô-la Đá Xay', 55000.00, 10.00, 1, N'Sô-cô-la Bỉ nguyên chất đá xay béo ngậy', 'https://images.unsplash.com/photo-1572490122747-3968b75cc699?q=80&w=300'),
(4, 'BA-TIR', N'Tiramisu', 49000.00, 10.00, 1, N'Bánh ngọt truyền thống hương vị cà phê và cacao', 'https://images.unsplash.com/photo-1578985545062-69928b1d9587?q=80&w=300');

-- 7. Insert BranchMenuItems (Phân phối toàn bộ món về toàn bộ chi nhánh)
INSERT INTO branch_menu_items (BranchID, MenuItemID, is_available, menu_itemid)
SELECT b.branchid, m.menu_itemid, 1, m.menu_itemid
FROM branches b CROSS JOIN menu_items m;

-- 8. Insert CustomizationGroups (Nhóm tùy chỉnh)
INSERT INTO customization_groups (group_name, min_select, max_select) VALUES
(N'Kích cỡ', 1, 1),
(N'Mức đường', 1, 1),
(N'Mức đá', 1, 1),
(N'Topping thêm', 0, 3);

-- 9. Insert CustomizationOptions (Tùy chọn chi tiết)
INSERT INTO customization_options (groupid, option_name, extra_price, is_available) VALUES
(1, N'Size S', -5000.00, 1),
(1, N'Size M', 0.00, 1),
(1, N'Size L', 10000.00, 1),
(2, N'100% Đường', 0.00, 1),
(2, N'70% Đường', 0.00, 1),
(2, N'50% Đường', 0.00, 1),
(2, N'0% Đường', 0.00, 1),
(3, N'100% Đá', 0.00, 1),
(3, N'70% Đá', 0.00, 1),
(3, N'50% Đá', 0.00, 1),
(3, N'0% Đá', 0.00, 1),
(4, N'Trân châu hoàng kim', 10000.00, 1),
(4, N'Thạch đào', 8000.00, 1),
(4, N'Kem phô mai', 12000.00, 1);

-- 10. Gán tùy chọn cho các món (Trừ Tiramisu không gán kích cỡ/đường/đá)
INSERT INTO menu_item_customizations (GroupID, MenuItemID, menu_itemid)
SELECT 1, menu_itemid, menu_itemid FROM menu_items WHERE sku <> 'BA-TIR';

INSERT INTO menu_item_customizations (GroupID, MenuItemID, menu_itemid)
SELECT 2, menu_itemid, menu_itemid FROM menu_items WHERE sku <> 'BA-TIR';

INSERT INTO menu_item_customizations (GroupID, MenuItemID, menu_itemid)
SELECT 3, menu_itemid, menu_itemid FROM menu_items WHERE sku <> 'BA-TIR';

INSERT INTO menu_item_customizations (GroupID, MenuItemID, menu_itemid)
SELECT 4, menu_itemid, menu_itemid FROM menu_items WHERE categoryid IN (1, 2, 3);

-- 11. Insert Coupons (Mã giảm giá)
INSERT INTO coupons (code, discount_type, discount_value, min_order_value, max_discount_amount, start_date, end_date, usage_limit, used_count, is_active) VALUES
('COFFEE10', 'Percentage', 10.00, 50000.00, 20000.00, '2026-01-01T00:00:00Z', '2026-12-31T23:59:59Z', 1000, 0, 1),
('CHAOBAN', 'FixedAmount', 20000.00, 60000.00, 20000.00, '2026-01-01T00:00:00Z', '2026-12-31T23:59:59Z', 500, 0, 1);
