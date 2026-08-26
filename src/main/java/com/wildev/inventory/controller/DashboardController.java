package com.wildev.inventory.controller;

import com.wildev.inventory.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("totalProducts",
                dashboardService.getTotalProducts());

        model.addAttribute("totalCategories",
                dashboardService.getTotalCategories());

        model.addAttribute("totalSales",
                dashboardService.getTotalSales());

        model.addAttribute("totalRevenue",
                dashboardService.getTotalRevenue());

        model.addAttribute("lowStockProducts",
                dashboardService.getLowStockProducts());

        return "dashboard/index";
    }
}
