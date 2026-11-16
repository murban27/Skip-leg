import { colors, typography, spacing, radii, shadows } from '../index';

describe('Design Tokens', () => {
  describe('Colors', () => {
    it('should have accent, background, and textPrimary defined and distinct', () => {
      expect(colors.accent).toBeDefined();
      expect(colors.background).toBeDefined();
      expect(colors.textPrimary).toBeDefined();
      
      expect(typeof colors.accent).toBe('string');
      expect(typeof colors.background).toBe('string');
      expect(typeof colors.textPrimary).toBe('string');
      
      expect(colors.accent).not.toEqual(colors.background);
      expect(colors.accent).not.toEqual(colors.textPrimary);
      expect(colors.background).not.toEqual(colors.textPrimary);
    });
  });

  describe('Typography', () => {
    it('should have all variants defined', () => {
      expect(typography.title).toBeDefined();
      expect(typography.heading).toBeDefined();
      expect(typography.body).toBeDefined();
      expect(typography.caption).toBeDefined();
      expect(typography.numeric).toBeDefined();
    });
  });

  describe('Spacing', () => {
    it('should have all spacing values defined', () => {
      expect(spacing.xs).toBeDefined();
      expect(spacing.sm).toBeDefined();
      expect(spacing.md).toBeDefined();
      expect(spacing.lg).toBeDefined();
      expect(spacing.xl).toBeDefined();
      expect(spacing.xxl).toBeDefined();
    });
  });

  describe('Radii', () => {
    it('should have all radii values defined', () => {
      expect(radii.sm).toBeDefined();
      expect(radii.md).toBeDefined();
      expect(radii.lg).toBeDefined();
      expect(radii.pill).toBeDefined();
    });
  });

  describe('Shadows', () => {
    it('should have all shadow values defined', () => {
      expect(shadows.card).toBeDefined();
      expect(shadows.button).toBeDefined();
    });
  });
});