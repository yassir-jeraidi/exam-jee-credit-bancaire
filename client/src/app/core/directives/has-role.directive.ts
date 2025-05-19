import { Directive, Input, TemplateRef, ViewContainerRef, OnDestroy } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Subscription } from 'rxjs';

@Directive({
  selector: '[appHasRole]',
  standalone: true
})
export class HasRoleDirective implements OnDestroy {
  private subscription: Subscription | null = null;

  constructor(
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef,
    private authService: AuthService
  ) {}

  @Input() set appHasRole(roles: string[]) {
    this.viewContainer.clear(); // Always start by clearing the view

    // Unsubscribe previous if any
    if (this.subscription) {
      this.subscription.unsubscribe();
    }

    this.subscription = this.authService.me().subscribe({
      next: (user) => {
        if (user && roles.includes(user.role)) {
          this.viewContainer.createEmbeddedView(this.templateRef);
        } else {
          this.viewContainer.clear();
        }
      },
      error: () => {
        this.viewContainer.clear(); // Hide view on error
      }
    });
  }

  ngOnDestroy() {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
